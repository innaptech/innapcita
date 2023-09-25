import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHistoriaMedicaAdulto } from 'app/shared/model/historia-medica-adulto.model';
import { HistoriaMedicaAdultoService } from './historia-medica-adulto.service';

@Component({
    selector: 'jhi-historia-medica-adulto-delete-dialog',
    templateUrl: './historia-medica-adulto-delete-dialog.component.html'
})
export class HistoriaMedicaAdultoDeleteDialogComponent {
    historiaMedicaAdulto: IHistoriaMedicaAdulto;

    constructor(
        protected historiaMedicaAdultoService: HistoriaMedicaAdultoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.historiaMedicaAdultoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'historiaMedicaAdultoListModification',
                content: 'Deleted an historiaMedicaAdulto'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-historia-medica-adulto-delete-popup',
    template: ''
})
export class HistoriaMedicaAdultoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ historiaMedicaAdulto }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(HistoriaMedicaAdultoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.historiaMedicaAdulto = historiaMedicaAdulto;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/historia-medica-adulto', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/historia-medica-adulto', { outlets: { popup: null } }]);
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
