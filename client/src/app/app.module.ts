import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './pages/login/login.component';
import {RegisterComponent} from './pages/register/register.component';
import {MenuComponent} from './components/menu/menu.component';
import {UserDashboardComponent} from './pages/user-dashboard/user-dashboard.component';
import {LightInfoComponent} from './components/light-info/light-info.component';
import {TransactionComponent} from './pages/transaction/transaction.component';
import {ContactsComponent} from './pages/contacts/contacts.component';
import {NewTransactionComponent} from './pages/new-transaction/new-transaction.component';
import {NewContactComponent} from './pages/new-contact/new-contact.component';
import {MyProfileComponent} from './pages/my-profile/my-profile.component';
import {ManagerUsersComponent} from './admin/manager-users/manager-users.component';
import {MainPageComponent} from './pages/main-page/main-page.component';
import {PageNotFoundComponent} from './pages/page-not-found/page-not-found.component';
import {MainPageAdminComponent} from './admin/main-page-admin/main-page-admin.component';
import {DashboardComponent} from './admin/dashboard/dashboard.component';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {HttpIntercepteurService} from "./services/http-intercepteur/http-intercepteur.service";
import {FormsModule} from "@angular/forms";
import {ConfirmRegisterComponent} from './pages/confirm-register/confirm-register.component';
import {DatepickerModule} from "ng2-datepicker";
import {DatePipe} from "@angular/common";
import { LogoutComponent } from './pages/logout/logout.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    MenuComponent,
    UserDashboardComponent,
    LightInfoComponent,
    TransactionComponent,
    ContactsComponent,
    NewTransactionComponent,
    NewContactComponent,
    MyProfileComponent,
    ManagerUsersComponent,
    MainPageComponent,
    PageNotFoundComponent,
    MainPageAdminComponent,
    DashboardComponent,
    ConfirmRegisterComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    DatepickerModule
  ],
  providers: [
    {
      provide : HTTP_INTERCEPTORS,
      useClass : HttpIntercepteurService,
      multi : true // c-v-d on peut avoir plusieurs intercepteurs
    },
    HttpClient,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
