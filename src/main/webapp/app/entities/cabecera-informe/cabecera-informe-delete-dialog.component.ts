import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICabeceraInforme } from 'app/shared/model/cabecera-informe.model';
import { CabeceraInformeService } from './cabecera-informe.service';

@Component({
    selector: 'jhi-cabecera-informe-delete-dialog',
    templateUrl: './cabecera-informe-delete-dialog.component.html'
})
export class CabeceraInformeDeleteDialogComponent {
    cabeceraInforme: ICabeceraInforme;

    constructor(
        protected cabeceraInformeService: CabeceraInformeService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cabeceraInformeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'cabeceraInformeListModification',
                content: 'Deleted an cabeceraInforme'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cabecera-informe-delete-popup',
    template: ''
})
export class CabeceraInformeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cabeceraInforme }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CabeceraInformeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.cabeceraInforme = cabeceraInforme;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/cabecera-informe', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/cabecera-informe', { outlets: { popup: null } }]);
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
