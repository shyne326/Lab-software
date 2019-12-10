import { Test } from './test.model';
import { Deserializable } from './deserializable.model';

export interface ValeurDeReference{

    id: number;
    gender?: string;
    value: string;
    category?: string;
    unit?: string;
    test: Test;
    createdOn?: Date;
    updatedOn?: Date;

    // For posting
    testId: number;

    /*deserialize(input: any){
        Object.assign(this, input);
        // for our user and map them to a proper `Test` model
        this.test = input.test.map(test => new Test().deserialize(test));

    return this;
    }
    */

}