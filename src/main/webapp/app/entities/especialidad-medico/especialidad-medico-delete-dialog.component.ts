import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEspecialidadMedico } from 'app/shared/model/especialidad-medico.model';
import { EspecialidadMedicoService } from './especialidad-medico.service';

@Component({
    selector: 'jhi-especialidad-medico-delete-dialog',
    templateUrl: './especialidad-medico-delete-dialog.component.html'
})
export class EspecialidadMedicoDeleteDialogComponent {
    especialidadMedico: IEspecialidadMedico;

    constructor(
        protected especialidadMedicoService: EspecialidadMedicoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.especialidadMedicoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'especialidadMedicoListModification',
                content: 'Deleted an especialidadMedico'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-especialidad-medico-delete-popup',
    template: ''
})
export class EspecialidadMedicoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ especialidadMedico }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EspecialidadMedicoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.especialidadMedico = especialidadMedico;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/especialidad-medico', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/especialidad-medico', { outlets: { popup: null } }]);
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
