import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEstatusPago } from 'app/shared/model/estatus-pago.model';
import { EstatusPagoService } from './estatus-pago.service';

@Component({
    selector: 'jhi-estatus-pago-delete-dialog',
    templateUrl: './estatus-pago-delete-dialog.component.html'
})
export class EstatusPagoDeleteDialogComponent {
    estatusPago: IEstatusPago;

    constructor(
        protected estatusPagoService: EstatusPagoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.estatusPagoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'estatusPagoListModification',
                content: 'Deleted an estatusPago'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-estatus-pago-delete-popup',
    template: ''
})
export class EstatusPagoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ estatusPago }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EstatusPagoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.estatusPago = estatusPago;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/estatus-pago', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/estatus-pago', { outlets: { popup: null } }]);
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
