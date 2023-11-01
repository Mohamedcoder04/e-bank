/* tslint:disable */
/* eslint-disable */
import { Account } from './account';
import { Address } from './address';
import { Contact } from './contact';
import { GrantedAuthority } from './granted-authority';
import { Role } from './role';
import { Token } from './token';
import { Transaction } from './transaction';
export interface User {
  account?: Account;
  accountNonExpired?: boolean;
  accountNonLocked?: boolean;
  active?: boolean;
  adress?: Address;
  authorities?: Array<GrantedAuthority>;
  contacts?: Array<Contact>;
  creationDate?: string;
  credentialsNonExpired?: boolean;
  email?: string;
  enabled?: boolean;
  firstName?: string;
  id?: number;
  lastName?: string;
  lastUpdated?: string;
  password?: string;
  role?: Role;
  tokens?: Array<Token>;
  transactions?: Array<Transaction>;
  username?: string;
}
