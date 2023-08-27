import { Aula } from "./aula";

export interface Curso {
    _id: string;
    name: string;
    category: string;
    aulas: Aula[];
}
