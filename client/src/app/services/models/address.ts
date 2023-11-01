/* tslint:disable */
/* eslint-disable */
import { User } from './user';
export interface Address {
  city?: string;
  country?: string;
  creationDate?: string;
  houseNumber?: number;
  id?: number;
  lastUpdated?: string;
  street?: string;
  user?: User;
  zipCode?: number;
}
