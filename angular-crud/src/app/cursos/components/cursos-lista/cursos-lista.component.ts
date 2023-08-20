import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Curso } from '../../model/curso';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-cursos-lista',
  templateUrl: './cursos-lista.component.html',
  styleUrls: ['./cursos-lista.component.scss']
})
export class CursosListaComponent {

  @Input() cursos: Curso[] = [];
  @Output() adicionar = new EventEmitter(false);
  @Output() editar = new EventEmitter(false);
  @Output() delete = new EventEmitter(false);

  readonly displayedColumns = ['_id', 'name', 'category','actions'];

  constructor(
    private router: Router,
    private routeActive: ActivatedRoute
  ) {}

  onAdd() {
    // this.router.navigate(['new'], {relativeTo: this.routeActive});
    this.adicionar.emit(true);
  }

  onEdit(curso: Curso) { 
    this.editar.emit(curso);
  }

  onDelete(curso: Curso) {
    this.delete.emit(curso);
  }
}
