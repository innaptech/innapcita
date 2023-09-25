import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEstatusCita } from 'app/shared/model/estatus-cita.model';
import { EstatusCitaService } from './estatus-cita.service';

@Component({
    selector: 'jhi-estatus-cita-delete-dialog',
    templateUrl: './estatus-cita-delete-dialog.component.html'
})
export class EstatusCitaDeleteDialogComponent {
    estatusCita: IEstatusCita;

    constructor(
        protected estatusCitaService: EstatusCitaService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.estatusCitaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'estatusCitaListModification',
                content: 'Deleted an estatusCita'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-estatus-cita-delete-popup',
    template: ''
})
export class EstatusCitaDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ estatusCita }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EstatusCitaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.estatusCita = estatusCita;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/estatus-cita', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/estatus-cita', { outlets: { popup: null } }]);
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
