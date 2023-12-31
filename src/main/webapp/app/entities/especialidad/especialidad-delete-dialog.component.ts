import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEspecialidad } from 'app/shared/model/especialidad.model';
import { EspecialidadService } from './especialidad.service';

@Component({
    selector: 'jhi-especialidad-delete-dialog',
    templateUrl: './especialidad-delete-dialog.component.html'
})
export class EspecialidadDeleteDialogComponent {
    especialidad: IEspecialidad;

    constructor(
        protected especialidadService: EspecialidadService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.especialidadService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'especialidadListModification',
                content: 'Deleted an especialidad'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-especialidad-delete-popup',
    template: ''
})
export class EspecialidadDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ especialidad }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EspecialidadDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.especialidad = especialidad;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/especialidad', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/especialidad', { outlets: { popup: null } }]);
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
