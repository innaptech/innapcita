import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoPago } from 'app/shared/model/tipo-pago.model';
import { TipoPagoService } from './tipo-pago.service';

@Component({
    selector: 'jhi-tipo-pago-delete-dialog',
    templateUrl: './tipo-pago-delete-dialog.component.html'
})
export class TipoPagoDeleteDialogComponent {
    tipoPago: ITipoPago;

    constructor(protected tipoPagoService: TipoPagoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoPagoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoPagoListModification',
                content: 'Deleted an tipoPago'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-pago-delete-popup',
    template: ''
})
export class TipoPagoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoPago }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoPagoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.tipoPago = tipoPago;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/tipo-pago', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/tipo-pago', { outlets: { popup: null } }]);
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
