import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPago } from 'app/shared/model/pago.model';
import { PagoService } from './pago.service';

@Component({
    selector: 'jhi-pago-delete-dialog',
    templateUrl: './pago-delete-dialog.component.html'
})
export class PagoDeleteDialogComponent {
    pago: IPago;

    constructor(protected pagoService: PagoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pagoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'pagoListModification',
                content: 'Deleted an pago'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-pago-delete-popup',
    template: ''
})
export class PagoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pago }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PagoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.pago = pago;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/pago', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/pago', { outlets: { popup: null } }]);
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
