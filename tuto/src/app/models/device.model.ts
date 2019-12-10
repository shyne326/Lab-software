import { Deserializable } from './deserializable.model';

export interface Device {


    // From https://nehalist.io/angular-7-models/

    id: number;
    name: string;
    description?: string;
    //testsEffectues: TestEffectue[];

   /* deserialize(input: any): this {
        return Object.assign(this, input);
    } */

}