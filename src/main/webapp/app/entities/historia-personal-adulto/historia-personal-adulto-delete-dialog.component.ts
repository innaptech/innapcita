import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHistoriaPersonalAdulto } from 'app/shared/model/historia-personal-adulto.model';
import { HistoriaPersonalAdultoService } from './historia-personal-adulto.service';

@Component({
    selector: 'jhi-historia-personal-adulto-delete-dialog',
    templateUrl: './historia-personal-adulto-delete-dialog.component.html'
})
export class HistoriaPersonalAdultoDeleteDialogComponent {
    historiaPersonalAdulto: IHistoriaPersonalAdulto;

    constructor(
        protected historiaPersonalAdultoService: HistoriaPersonalAdultoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.historiaPersonalAdultoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'historiaPersonalAdultoListModification',
                content: 'Deleted an historiaPersonalAdulto'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-historia-personal-adulto-delete-popup',
    template: ''
})
export class HistoriaPersonalAdultoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ historiaPersonalAdulto }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(HistoriaPersonalAdultoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.historiaPersonalAdulto = historiaPersonalAdulto;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/historia-personal-adulto', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/historia-personal-adulto', { outlets: { popup: null } }]);
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
