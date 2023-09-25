import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMonedaCambio } from 'app/shared/model/moneda-cambio.model';
import { MonedaCambioService } from './moneda-cambio.service';

@Component({
    selector: 'jhi-moneda-cambio-delete-dialog',
    templateUrl: './moneda-cambio-delete-dialog.component.html'
})
export class MonedaCambioDeleteDialogComponent {
    monedaCambio: IMonedaCambio;

    constructor(
        protected monedaCambioService: MonedaCambioService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.monedaCambioService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'monedaCambioListModification',
                content: 'Deleted an monedaCambio'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-moneda-cambio-delete-popup',
    template: ''
})
export class MonedaCambioDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ monedaCambio }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MonedaCambioDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.monedaCambio = monedaCambio;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/moneda-cambio', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/moneda-cambio', { outlets: { popup: null } }]);
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
