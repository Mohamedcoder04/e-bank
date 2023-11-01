import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {UserDashboardComponent} from "./pages/user-dashboard/user-dashboard.component";
import {TransactionComponent} from "./pages/transaction/transaction.component";
import {MainPageComponent} from "./pages/main-page/main-page.component";
import {ContactsComponent} from "./pages/contacts/contacts.component";
import {NewTransactionComponent} from "./pages/new-transaction/new-transaction.component";
import {NewContactComponent} from "./pages/new-contact/new-contact.component";
import {MyProfileComponent} from "./pages/my-profile/my-profile.component";
import {PageNotFoundComponent} from "./pages/page-not-found/page-not-found.component";
import {MainPageAdminComponent} from "./admin/main-page-admin/main-page-admin.component";
import {ManagerUsersComponent} from "./admin/manager-users/manager-users.component";
import {DashboardComponent} from "./admin/dashboard/dashboard.component";
import {ConfirmRegisterComponent} from "./pages/confirm-register/confirm-register.component";
import {TokenGuardService} from "./services/guard/token-guard/token-guard.service";
import {AdminGuardService} from "./services/guard/admin-guard/admin-guard.service";
import {LogoutComponent} from "./pages/logout/logout.component";

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },{
    path: 'login',
    component: LoginComponent
  },{
    path: 'logout',
    component: LogoutComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'confirm-register',
    component: ConfirmRegisterComponent
  },
  {
    path: 'user',
    component: MainPageComponent,
    canActivate:[ TokenGuardService],
    children: [
      {
        path: 'dashboard',
        component: UserDashboardComponent
      },
      {
        path: 'my-transactions',
        component: TransactionComponent
      },
      {
        path: 'my-contact-list',
        component: ContactsComponent
      },
      {
        path: 'new-contact/:idContact',
        component: NewContactComponent
      },
      {
        path: 'new-transaction',
        component: NewTransactionComponent
      },
      {
        path: 'new-contact',
        component: NewContactComponent
      },
      {
        path: 'profile',
        component: MyProfileComponent
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      }
    ]
  },
  {
    path: 'admin',
    component: MainPageAdminComponent,
    canActivate:[ TokenGuardService, AdminGuardService ],
    children: [
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: 'customers',
        component: ManagerUsersComponent
      },
      {
        path: 'profile',
        component: MyProfileComponent
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      }
    ]
  },
  {
    path: '**', component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
