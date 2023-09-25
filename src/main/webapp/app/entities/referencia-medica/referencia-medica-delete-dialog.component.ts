import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReferenciaMedica } from 'app/shared/model/referencia-medica.model';
import { ReferenciaMedicaService } from './referencia-medica.service';

@Component({
    selector: 'jhi-referencia-medica-delete-dialog',
    templateUrl: './referencia-medica-delete-dialog.component.html'
})
export class ReferenciaMedicaDeleteDialogComponent {
    referenciaMedica: IReferenciaMedica;

    constructor(
        protected referenciaMedicaService: ReferenciaMedicaService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.referenciaMedicaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'referenciaMedicaListModification',
                content: 'Deleted an referenciaMedica'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-referencia-medica-delete-popup',
    template: ''
})
export class ReferenciaMedicaDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ referenciaMedica }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ReferenciaMedicaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.referenciaMedica = referenciaMedica;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/referencia-medica', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/referencia-medica', { outlets: { popup: null } }]);
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
