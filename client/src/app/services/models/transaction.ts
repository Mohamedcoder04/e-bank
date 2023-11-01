/* tslint:disable */
/* eslint-disable */
import { User } from './user';
export interface Transaction {
  amount?: number;
  creationDate?: string;
  destinationIban?: string;
  id?: number;
  lastUpdated?: string;
  transactionDate?: string;
  type?: 'TRANSFERT' | 'DEPOSIT';
  user?: User;
}
