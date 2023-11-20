import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

import { SuccessPopupComponent } from 'src/app/modules/shared/shared/components/success-popup/success-popup.component';
import { HardcodedAuthenticationService } from '../../service/harcoded-authentication.service';
import {
  CandidateRegistrationRequest,
  RecruiterRegistrationRequest,
} from '../../models/request-model';
import { CoreService } from '../../service/authentication.service';
import { RegistrationResponse } from '../../models/response.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  userName: string = '';
  passWord: string = '';
  validLogin?: boolean = true;
  loginForm = this.fb.group({
    username: ['', Validators.required],
    userpassword: ['', Validators.required],
  });
  registrationFormForCategory!: FormGroup;
  categoryArr: string[] = ['Candidate', 'Recruiter', 'Interviewer'];
  showRegistrationForm: boolean = false;
  selectedDropdown: string = '';
  showLoginForm: boolean = false;
  selectedCategory: string = '';
  showPassword: boolean = false;
  showSuccessModal: boolean = false;

  constructor(
    private fb: FormBuilder,
    public matDialog: MatDialog,
    private router: Router,
    public hardcodedAuthenticationService: HardcodedAuthenticationService,
    private coreService: CoreService
  ) {
    this.initialiseRegistrationForm();
  }

  ngOnInit(): void {}

  login1(): void {
    if (
      this.hardcodedAuthenticationService.authenticateUser(
        this.userName?.toLowerCase(),
        this.passWord?.toLowerCase()
      )
    ) {
      this.validLogin = true;
      const dialogRef = this.matDialog.open(SuccessPopupComponent, {
        width: '744px',
        panelClass: 'success-popup-container',
        disableClose: true,
        data: {
          successMsg: 'You have logged in successfully',
        },
      });
      dialogRef.afterClosed().subscribe((result: any) => {
        if (result?.closeStatus?.toLowerCase() == 'closed') {
          this.router.navigate(['landing-page']);
        }
      });
    } else {
      this.validLogin = false;
    }
    this.initialiseLoginForm();
  }

  initialiseLoginForm(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      userpassword: ['', Validators.required],
    });
  }

  initialiseRegistrationForm(): void {
    this.registrationFormForCategory = this.fb.group(
      {
        name: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(8)]],
        confirmPassword: ['', [Validators.required]],
      },
      {
        validators: this.passwordMatchValidator,
      }
    );
  }

  passwordMatchValidator(
    control: AbstractControl
  ): { [key: string]: boolean } | null {
    const password = control.get('password');
    const confirmPassword = control.get('confirmPassword');
    return password &&
      confirmPassword &&
      password.value !== confirmPassword.value
      ? { passwordMismatch: true }
      : null;
  }

  forgotPassword(): void {
    alert('Forgot Password');
  }

  loginForCategory(selectedCategory: string): void {
    this.selectedCategory = selectedCategory;
    this.showLoginForm = true;
  }

  login(selectedCategory: string): void {
    switch (selectedCategory.toLowerCase()) {
      case 'candidate':
        this.loginAsCandidate();
        break;
      case 'recruiter':
        this.loginAsRecruiter();
        break;
      case 'interviewer':
        this.loginAsInterviewer();
        break;

      default:
        break;
    }
  }

  loginAsCandidate(): void {
    sessionStorage.setItem('loggedin', JSON.stringify(true));
    sessionStorage.setItem('authenticatedUser', this.loginForm.controls['username'].value);
    sessionStorage.setItem('role', 'candidate');
    this.coreService.triggerMethod();
    this.router.navigate(['landing-page'], { queryParams: { category: 'Candidate' } });

  }

  loginAsRecruiter(): void {
    sessionStorage.setItem('loggedin', JSON.stringify(true));
    sessionStorage.setItem('authenticatedUser', this.loginForm.controls['username'].value);
    sessionStorage.setItem('role', 'recruiter');
    this.coreService.triggerMethod();
    this.router.navigate(['landing-page'], { queryParams: { category: 'Recruiter' } });
  }

  loginAsInterviewer(): void {
    alert('loginAsInterviewer');
  }

  registerCategoryForSignUp(): void {
    this.initialiseRegistrationForm();
    this.showRegistrationForm = true;
    this.showLoginForm = !this.showLoginForm;
  }

  register(categorySelected: string): void {
    switch (categorySelected.toLowerCase()) {
      case 'candidate':
        this.candidateRegistration();
        break;
      case 'recruiter':
        this.recruiterRegistration();
        break;
      default:
        break;
    }
  }

  candidateRegistration(): void {
    const payload: CandidateRegistrationRequest = {
      candidateName: this.registrationFormForCategory.controls['name'].value,
      candidateEmail: this.registrationFormForCategory.controls['email'].value,
      candidatePassword: btoa(
        unescape(
          encodeURIComponent(
            this.registrationFormForCategory.controls['password'].value
          )
        )
      ),
    };
    this.coreService
      .registerCandidate(payload)
      .subscribe((res: RegistrationResponse) => {
        if (
          res.message
            .toLowerCase()
            .includes('candidate registered successfully') &&
          res.statusCode === '200'
        ) {
          this.showSuccessModal = true;
          this.openTheDialog(
            'You have Registered successfully. Please go to login page & sign in with your credentials'
          );
          this.showPassword = false;
        } else {
          this.showSuccessModal = false;
          alert('Something went wrong!');
        }
      });
  }

  recruiterRegistration(): void {
    const payload: RecruiterRegistrationRequest = {
      recruiterName: this.registrationFormForCategory.controls['name'].value,
      recruiterEmail: this.registrationFormForCategory.controls['email'].value,
      recruiterPassword: btoa(
        unescape(
          encodeURIComponent(
            this.registrationFormForCategory.controls['password'].value
          )
        )
      ),
    };
    this.coreService
      .registerRecruiter(payload)
      .subscribe((res: RegistrationResponse) => {
        if (
          res.message
            .toLowerCase()
            .includes('recruiter registered successfully') &&
          res.statusCode === '200'
        ) {
          this.showSuccessModal = true;
          this.openTheDialog(
            'You have Registered successfully. Please go to login page & sign in with your credentials'
          );
          this.showPassword = false;
        } else {
          this.showSuccessModal = false;
          alert('Something went wrong!');
        }
      });
  }

  hideRegistrationForm(): void {
    this.showRegistrationForm = false;
    this.showLoginForm = true;
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  openTheDialog(msgToDisplay: string): void {
    const dialogRef = this.matDialog.open(SuccessPopupComponent, {
      width: '744px',
      panelClass: 'success-popup-container',
      disableClose: true,
      data: {
        successMsg: msgToDisplay,
      },
    });
    dialogRef.afterClosed().subscribe((result: any) => {
      if (result?.closeStatus?.toLowerCase() == 'closed') {
        this.registrationFormForCategory.reset();
      }
    });
  }
}
