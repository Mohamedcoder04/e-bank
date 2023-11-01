/* tslint:disable */
/* eslint-disable */
import { User } from './user';
export interface Token {
  expired?: boolean;
  id?: number;
  invoked?: boolean;
  token?: string;
  user?: User;
}
