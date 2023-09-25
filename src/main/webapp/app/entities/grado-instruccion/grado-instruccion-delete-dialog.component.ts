import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGradoInstruccion } from 'app/shared/model/grado-instruccion.model';
import { GradoInstruccionService } from './grado-instruccion.service';

@Component({
    selector: 'jhi-grado-instruccion-delete-dialog',
    templateUrl: './grado-instruccion-delete-dialog.component.html'
})
export class GradoInstruccionDeleteDialogComponent {
    gradoInstruccion: IGradoInstruccion;

    constructor(
        protected gradoInstruccionService: GradoInstruccionService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.gradoInstruccionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'gradoInstruccionListModification',
                content: 'Deleted an gradoInstruccion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-grado-instruccion-delete-popup',
    template: ''
})
export class GradoInstruccionDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ gradoInstruccion }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GradoInstruccionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.gradoInstruccion = gradoInstruccion;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/grado-instruccion', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/grado-instruccion', { outlets: { popup: null } }]);
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
