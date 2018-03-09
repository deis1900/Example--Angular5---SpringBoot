export class User {

  constructor(public id: number,
              public firstName: string,
              public lastName: string,
              public userName: string,
              public password: string,
              public email: string,
              public sex: string,
              public phone: number,
              public access: boolean,
              public image: string) { }
}
