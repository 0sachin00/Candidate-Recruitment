export const NAV_MENU = {
  MENU_ITEMS_FOR_CANDIDATE: [
    {
      menuID: '1',
      parentMenu: 'My Dashboard',
      category: 'Candidate',
      subMenus: [
        {
          id: 1,
          name: 'Jobs Available',
          status: false,
          icon: 'home',
          altValue: 'Jobs Available',
          routerUrl: '/manage-client-presentation',
          parentMenu: 'My Dashboard',
        },
        {
          id: 2,
          name: 'Jobs Applied',
          status: false,
          icon: 'home',
          altValue: 'Jobs Applied',
          routerUrl: '/app/landing-page',
          parentMenu: 'My Dashboard',
        },
        {
          id: 3,
          name: 'Jobs Selected',
          status: false,
          icon: 'home',
          altValue: 'Jobs Selected',
          routerUrl: '/my-assets',
          parentMenu: 'My Dashboard',
        },
      ],
    },
  ],
  MENU_ITEMS_FOR_RECRUITER: [{
    menuID: '2',
    parentMenu: 'My Dashboard',
    category: 'Recruiter',
    subMenus: [
      {
        id: 1,
        name: 'Post a Job',
        status: false,
        icon: 'home',
        altValue: 'Post a Job',
        routerUrl: '/app/landing-page',
        parentMenu: 'My Dashboard',
      },
      {
        id: 2,
        name: 'Jobs Posted',
        status: false,
        icon: 'home',
        altValue: 'Jobs Posted',
        routerUrl: '/app/landing-page',
        parentMenu: 'My Dashboard',
      }
    ],
  }],
};
