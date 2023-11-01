/* tslint:disable */
/* eslint-disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpContext } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { AccountDto } from '../models/account-dto';

@Injectable({
  providedIn: 'root',
})
export class AccountService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation findAll4
   */
  static readonly FindAll4Path = '/accounts/';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAll4()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAll4$Response(params?: {
  },
  context?: HttpContext

): Observable<StrictHttpResponse<Array<AccountDto>>> {

    const rb = new RequestBuilder(this.rootUrl, AccountService.FindAll4Path, 'get');
    if (params) {
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json',
      context: context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Array<AccountDto>>;
      })
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAll4$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAll4(params?: {
  },
  context?: HttpContext

): Observable<Array<AccountDto>> {

    return this.findAll4$Response(params,context).pipe(
      map((r: StrictHttpResponse<Array<AccountDto>>) => r.body as Array<AccountDto>)
    );
  }

  /**
   * Path part for operation save4
   */
  static readonly Save4Path = '/accounts/';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `save4()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  save4$Response(params: {
    body: AccountDto
  },
  context?: HttpContext

): Observable<StrictHttpResponse<AccountDto>> {

    const rb = new RequestBuilder(this.rootUrl, AccountService.Save4Path, 'post');
    if (params) {
      rb.body(params.body, 'application/json');
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json',
      context: context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<AccountDto>;
      })
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `save4$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  save4(params: {
    body: AccountDto
  },
  context?: HttpContext

): Observable<AccountDto> {

    return this.save4$Response(params,context).pipe(
      map((r: StrictHttpResponse<AccountDto>) => r.body as AccountDto)
    );
  }

  /**
   * Path part for operation findById4
   */
  static readonly FindById4Path = '/accounts/account/{account-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findById4()` instead.
   *
   * This method doesn't expect any request body.
   */
  findById4$Response(params: {
    'account-id': number;
  },
  context?: HttpContext

): Observable<StrictHttpResponse<AccountDto>> {

    const rb = new RequestBuilder(this.rootUrl, AccountService.FindById4Path, 'get');
    if (params) {
      rb.path('account-id', params['account-id'], {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json',
      context: context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<AccountDto>;
      })
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findById4$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findById4(params: {
    'account-id': number;
  },
  context?: HttpContext

): Observable<AccountDto> {

    return this.findById4$Response(params,context).pipe(
      map((r: StrictHttpResponse<AccountDto>) => r.body as AccountDto)
    );
  }

  /**
   * Path part for operation delete4
   */
  static readonly Delete4Path = '/accounts/account/{account-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delete4()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete4$Response(params: {
    'account-id': number;
  },
  context?: HttpContext

): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AccountService.Delete4Path, 'delete');
    if (params) {
      rb.path('account-id', params['account-id'], {});
    }

    return this.http.request(rb.build({
      responseType: 'text',
      accept: '*/*',
      context: context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return (r as HttpResponse<any>).clone({ body: undefined }) as StrictHttpResponse<void>;
      })
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `delete4$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete4(params: {
    'account-id': number;
  },
  context?: HttpContext

): Observable<void> {

    return this.delete4$Response(params,context).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

}
