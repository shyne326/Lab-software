import { Test } from './test.model';
import { Deserializable } from './deserializable.model';

export interface Section{

    id: number;
    name: string;
    description?: string;
    tests?: Test[];
    createdOn? : Date;
    updatedOn?: Date;

    /*deserialize(input: any){
        Object.assign(this, input);
        this.tests = input.tests.map(car => new Test().deserialize(car));

        return this;
    }
    */
}