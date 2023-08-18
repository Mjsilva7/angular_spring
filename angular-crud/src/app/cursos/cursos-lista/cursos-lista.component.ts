import { Component, Input } from '@angular/core';
import { Curso } from '../model/curso';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-cursos-lista',
  templateUrl: './cursos-lista.component.html',
  styleUrls: ['./cursos-lista.component.scss']
})
export class CursosListaComponent {

  @Input() cursos: Curso[] = [];
  readonly displayedColumns = ['_id', 'name', 'category','actions'];

  constructor(
    private router: Router,
    private routeActive: ActivatedRoute
  ) {}

  onAdd() {
    this.router.navigate(['new'], {relativeTo: this.routeActive});
  }
}
