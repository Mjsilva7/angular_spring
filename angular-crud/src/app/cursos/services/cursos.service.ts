import { Injectable } from '@angular/core';
import { Curso } from '../model/curso';
import { HttpClient } from '@angular/common/http';
import { delay, first, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CursosService {

  private readonly API = 'api/cursos';

  constructor(private httpClient: HttpClient) { }

  loadById(id: string) {
    return this.httpClient.get<Curso>(`${this.API}/${id}`)
  }

  list() {
    return this.httpClient.get<Curso[]>(this.API)
    .pipe(
      first(),
      // delay(5000),
      tap(cursos => console.log(cursos))
    );
  }

  save(curso: Partial<Curso>) {
    if(curso._id) {
      return this.update(curso);
    }
      return this.create(curso);
  }

  private create(curso: Partial<Curso>) {
   return this.httpClient.post<Curso>(this.API, curso).pipe(first());
  }

  private  update(curso: Partial<Curso>) {
    return this.httpClient.put<Curso>(`${this.API}/${curso._id}`, curso).pipe(first());
  }

  delete(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }
}
