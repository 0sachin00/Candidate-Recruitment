import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { NAV_MENU } from '../app/constants/app-constants';
import { HardcodedAuthenticationService } from './modules/core/service/harcoded-authentication.service';
import { CoreService } from './modules/core/service/authentication.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  isShowing: boolean = true;
  @ViewChild('sidenav', { static: false }) sidenav?: ElementRef;
  menuItems: any[] = [];

  constructor(
    public router: Router,
    private coreService: CoreService
  ) {}
  ngOnInit(): void {
    // this.HardcodedAuthenticationService.removeSessionToLogOut();
    this.checkForMenuList();
    //below method is triggered when user logs in
    this.coreService.getMethodTrigger().subscribe(() => {
      this.checkForMenuList();
    });
  }

  toggleSidenav() {
    this.isShowing = !this.isShowing;
  }
  toggleSideNavMethod(resp: { toggle: boolean }): void {
    if (resp?.toggle === true) {
      this.toggleSidenav();
    }
  }

  activateSubMenu(subMenuItem: any): void {
    this.menuItems.forEach((eachMenu: any) => {
      eachMenu?.subMenus?.forEach((subMenuele: any) => {
        if (subMenuele?.id == subMenuItem?.id) {
          subMenuele.status = true;
        } else {
          subMenuele.status = false;
        }
      });
    });
  }

  checkForMenuList(): void {
    switch (sessionStorage.getItem('role')?.toLowerCase()) { 
      case 'candidate':
        this.menuItems = NAV_MENU.MENU_ITEMS_FOR_CANDIDATE;
        break;
      case 'recruiter':
        this.menuItems = NAV_MENU.MENU_ITEMS_FOR_RECRUITER;
        break; 
    }
    this.isShowing = true;
    const activeMenu = this.menuItems[0]?.subMenus.find(
      (eachSubMenu: any) => eachSubMenu?.id === 1
    );
    this.activateSubMenu(activeMenu);
  }

}
