import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRangoHoras } from 'app/shared/model/rango-horas.model';
import { RangoHorasService } from './rango-horas.service';

@Component({
    selector: 'jhi-rango-horas-delete-dialog',
    templateUrl: './rango-horas-delete-dialog.component.html'
})
export class RangoHorasDeleteDialogComponent {
    rangoHoras: IRangoHoras;

    constructor(
        protected rangoHorasService: RangoHorasService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.rangoHorasService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'rangoHorasListModification',
                content: 'Deleted an rangoHoras'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-rango-horas-delete-popup',
    template: ''
})
export class RangoHorasDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rangoHoras }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RangoHorasDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.rangoHoras = rangoHoras;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/rango-horas', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/rango-horas', { outlets: { popup: null } }]);
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
