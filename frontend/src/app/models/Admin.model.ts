export class AdminModel {

  public id: number;
  public name: string;
  public password: string;


  constructor(id: number, name: string, password: string) {
    this.id = id;
    this.name = name;
    this.password = password;
  }
}
