import { Component } from '@angular/core';
import { Curso } from '../../model/curso';
import { CursosService } from '../../services/cursos.service';
import { Observable, catchError, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/error-dialog/error-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.scss']
})
export class CursosComponent {

  cursos$: Observable<Curso[]>;  

  constructor(
    private cursosService: CursosService,
    public dialog: MatDialog,
    private router: Router,
    private routeActive: ActivatedRoute
  ) {
    this.cursos$ = this.cursosService.list()
    .pipe(
      catchError(error => {
        this.onError('Erro ao carregar dados.');
        return of([])
      })
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  onAdd() {
    this.router.navigate(['new'], {relativeTo: this.routeActive});
  }

}
