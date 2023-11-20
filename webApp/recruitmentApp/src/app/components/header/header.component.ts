import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { HardcodedAuthenticationService } from 'src/app/modules/core/service/harcoded-authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  userDropdown: boolean = false;
  userName: string | null = 'User' as string;
  category: string = '';
  @Output() toggleSideNavEvent = new EventEmitter();

  constructor(
    private HardcodedAuthenticationService: HardcodedAuthenticationService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    if(sessionStorage.getItem('authenticatedUser') != null){
      this.userName = sessionStorage.getItem('authenticatedUser') ? sessionStorage.getItem('authenticatedUser') : 'User';
    }
    this.activatedRoute.queryParams.subscribe(params => {
      this.category =  params['category'];
    });
  }

  signOut(): void {
    this.HardcodedAuthenticationService.removeSessionToLogOut();
    this.router.navigate(['accounts']);
  }
  toggleSideNav(): void {
    this.toggleSideNavEvent.emit({ toggle: true });
  }
}
