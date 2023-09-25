import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExamenComplementario } from 'app/shared/model/examen-complementario.model';
import { ExamenComplementarioService } from './examen-complementario.service';

@Component({
    selector: 'jhi-examen-complementario-delete-dialog',
    templateUrl: './examen-complementario-delete-dialog.component.html'
})
export class ExamenComplementarioDeleteDialogComponent {
    examenComplementario: IExamenComplementario;

    constructor(
        protected examenComplementarioService: ExamenComplementarioService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.examenComplementarioService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'examenComplementarioListModification',
                content: 'Deleted an examenComplementario'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-examen-complementario-delete-popup',
    template: ''
})
export class ExamenComplementarioDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ examenComplementario }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ExamenComplementarioDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.examenComplementario = examenComplementario;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/examen-complementario', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/examen-complementario', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
