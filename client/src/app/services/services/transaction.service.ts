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

import { TransactionDto } from '../models/transaction-dto';

@Injectable({
  providedIn: 'root',
})
export class TransactionService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation findAll1
   */
  static readonly FindAll1Path = '/transactions/';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAll1()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAll1$Response(params?: {
  },
  context?: HttpContext

): Observable<StrictHttpResponse<Array<TransactionDto>>> {

    const rb = new RequestBuilder(this.rootUrl, TransactionService.FindAll1Path, 'get');
    if (params) {
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json',
      context: context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Array<TransactionDto>>;
      })
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAll1$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAll1(params?: {
  },
  context?: HttpContext

): Observable<Array<TransactionDto>> {

    return this.findAll1$Response(params,context).pipe(
      map((r: StrictHttpResponse<Array<TransactionDto>>) => r.body as Array<TransactionDto>)
    );
  }

  /**
   * Path part for operation save1
   */
  static readonly Save1Path = '/transactions/';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `save1()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  save1$Response(params: {
    body: TransactionDto
  },
  context?: HttpContext

): Observable<StrictHttpResponse<TransactionDto>> {

    const rb = new RequestBuilder(this.rootUrl, TransactionService.Save1Path, 'post');
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
        return r as StrictHttpResponse<TransactionDto>;
      })
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `save1$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  save1(params: {
    body: TransactionDto
  },
  context?: HttpContext

): Observable<TransactionDto> {

    return this.save1$Response(params,context).pipe(
      map((r: StrictHttpResponse<TransactionDto>) => r.body as TransactionDto)
    );
  }

  /**
   * Path part for operation findAllByUserId
   */
  static readonly FindAllByUserIdPath = '/transactions/user/{user-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllByUserId()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllByUserId$Response(params: {
    'user-id': number;
  },
  context?: HttpContext

): Observable<StrictHttpResponse<Array<TransactionDto>>> {

    const rb = new RequestBuilder(this.rootUrl, TransactionService.FindAllByUserIdPath, 'get');
    if (params) {
      rb.path('user-id', params['user-id'], {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json',
      context: context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Array<TransactionDto>>;
      })
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllByUserId$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllByUserId(params: {
    'user-id': number;
  },
  context?: HttpContext

): Observable<Array<TransactionDto>> {

    return this.findAllByUserId$Response(params,context).pipe(
      map((r: StrictHttpResponse<Array<TransactionDto>>) => r.body as Array<TransactionDto>)
    );
  }

  /**
   * Path part for operation findById1
   */
  static readonly FindById1Path = '/transactions/transaction/{transaction-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findById1()` instead.
   *
   * This method doesn't expect any request body.
   */
  findById1$Response(params: {
    'transaction-id': number;
  },
  context?: HttpContext

): Observable<StrictHttpResponse<TransactionDto>> {

    const rb = new RequestBuilder(this.rootUrl, TransactionService.FindById1Path, 'get');
    if (params) {
      rb.path('transaction-id', params['transaction-id'], {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json',
      context: context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<TransactionDto>;
      })
    );
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findById1$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findById1(params: {
    'transaction-id': number;
  },
  context?: HttpContext

): Observable<TransactionDto> {

    return this.findById1$Response(params,context).pipe(
      map((r: StrictHttpResponse<TransactionDto>) => r.body as TransactionDto)
    );
  }

  /**
   * Path part for operation delete1
   */
  static readonly Delete1Path = '/transactions/transaction/{transaction-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delete1()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete1$Response(params: {
    'transaction-id': number;
  },
  context?: HttpContext

): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, TransactionService.Delete1Path, 'delete');
    if (params) {
      rb.path('transaction-id', params['transaction-id'], {});
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
   * To access the full response (for headers, for example), `delete1$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete1(params: {
    'transaction-id': number;
  },
  context?: HttpContext

): Observable<void> {

    return this.delete1$Response(params,context).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

}
