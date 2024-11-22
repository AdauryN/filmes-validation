import { Injectable } from '@angular/core';

const USER = 'cinemeet-user';

@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() { }
  static saveUser(user: any): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }


  static isUserLoggedIn(): boolean {
    if (this.getUser() === null) {
      return false;
    }
    const role: string = this.getUserRole();
    return role == 'USER';
  }

  static getUserRole(): string {
    const user = this.getUser();
    if (user == null) { return ''; }
    return user.role;
  }


  static getUser(): any {
    return JSON.parse(localStorage.getItem(USER));
  }


  static getUserId(): string {
    const user = this.getUser();
    if (user == null) { return ''; }
    return user.id;
  }

  static getUserName(): string {
    const user = this.getUser();
    if (user == null) { return ''; }
    return user.name;
  }


  static signOut(): void {
    window.localStorage.removeItem(USER);
  }

}

