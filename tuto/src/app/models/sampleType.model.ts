import { Sample } from './sample.model';
import { Test } from './test.model';
import { Deserializable } from './deserializable.model';

export interface SampleType{
//  tests: Test[];

    id: number;
    name: string;
    testsThatCanBeConducted?: Test[];
    samples?: Sample[];
    createdOn?: Date;
    updatedOn?: Date;

    /*deserialize(input: any){
        Object.assign(this, input);
        this.samples = input.samples.map(s => new Sample().deserialize(s));
        this.testsThatCanBeConducted = input.testsThatCanBeConducted.map(car => new Test().deserialize(car));
    
        return this;
    }
    */
}