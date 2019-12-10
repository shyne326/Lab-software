import { Deserializable } from './deserializable.model';

export interface User{

     id: number;
     firstName: string;
     lastName: string;
     email?: string;
     password?: string;
     dob: Date;
     sex: string;
     sigle: string;
     nationality: string;
     cni: string;
     photo?: string;
     address: string;
     codeUtilisateur?: string;
     phone: string; 
     createdOn?: Date;
     updatedOn?: Date;
     active?: boolean;


     /*deserialize(input: any){
        Object.assign(this, input);

          this.sex = input.sex.map(se => new Sexe().deserialize(se));
          this.sigle = input.sigle.map(si => new Sexe().deserialize(si));

        return this;
     }
     */

     /*getFullName(): string{
          return this.firstName + ' ' + this.lastName;
     }*/
}
