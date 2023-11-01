/* tslint:disable */
/* eslint-disable */
import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiConfiguration, ApiConfigurationParams } from './api-configuration';

import { UserService } from './services/user.service';
import { TransactionService } from './services/transaction.service';
import { AuthenticationService } from './services/authentication.service';
import { ContactService } from './services/contact.service';
import { AddressService } from './services/address.service';
import { AccountService } from './services/account.service';
import { StaticsService } from './services/statics.service';

/**
 * Module that provides all services and configuration.
 */
@NgModule({
  imports: [],
  exports: [],
  declarations: [],
  providers: [
    UserService,
    TransactionService,
    AuthenticationService,
    ContactService,
    AddressService,
    AccountService,
    StaticsService,
    ApiConfiguration
  ],
})
export class ApiModule {
  static forRoot(params: ApiConfigurationParams): ModuleWithProviders<ApiModule> {
    return {
      ngModule: ApiModule,
      providers: [
        {
          provide: ApiConfiguration,
          useValue: params
        }
      ]
    }
  }

  constructor( 
    @Optional() @SkipSelf() parentModule: ApiModule,
    @Optional() http: HttpClient
  ) {
    if (parentModule) {
      throw new Error('ApiModule is already loaded. Import in your base AppModule only.');
    }
    if (!http) {
      throw new Error('You need to import the HttpClientModule in your AppModule! \n' +
      'See also https://github.com/angular/angular/issues/20575');
    }
  }
}
