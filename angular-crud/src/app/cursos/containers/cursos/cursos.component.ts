import { Component } from '@angular/core';
import { Curso } from '../../model/curso';
import { CursosService } from '../../services/cursos.service';
import { Observable, catchError, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.scss']
})
export class CursosComponent {

  cursos$: Observable<Curso[]> | null = null;

  constructor(
    private cursosService: CursosService,
    public dialog: MatDialog,
    private router: Router,
    private routeActive: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.refresh();
  }

  refresh() {
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

  onEdit(curso: Curso) {
    this.router.navigate(['edit', curso._id], {relativeTo: this.routeActive});
  }

  onDelete(curso: Curso) {


      const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
        data: 'Tem certeza que deseja remover este curso?',
      });

      dialogRef.afterClosed().subscribe((result: boolean) => {
        if(result) {
          this.cursosService.delete(curso._id).subscribe(
            () => {
              this.refresh();
              this.snackBar.open('Curso removido com sucesso!', 'X', {
                duration: 5000,
                verticalPosition: 'top',
                horizontalPosition: 'center'
              });
            },
            error => this.onError('Erro ao tentar excluir curso.')
          );
        }
      });
    }
}
