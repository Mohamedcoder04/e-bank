/* tslint:disable */
/* eslint-disable */
import { AddressDto } from './address-dto';
import { Role } from './role';
export interface UserDto {
  active?: boolean;
  addressDto?: AddressDto;
  email: string;
  firstName: string;
  iban?: string;
  id?: number;
  lastName: string;
  password: string;
  role?: Role;
}
