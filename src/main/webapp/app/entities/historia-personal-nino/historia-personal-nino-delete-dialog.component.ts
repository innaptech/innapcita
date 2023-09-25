import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHistoriaPersonalNino } from 'app/shared/model/historia-personal-nino.model';
import { HistoriaPersonalNinoService } from './historia-personal-nino.service';

@Component({
    selector: 'jhi-historia-personal-nino-delete-dialog',
    templateUrl: './historia-personal-nino-delete-dialog.component.html'
})
export class HistoriaPersonalNinoDeleteDialogComponent {
    historiaPersonalNino: IHistoriaPersonalNino;

    constructor(
        protected historiaPersonalNinoService: HistoriaPersonalNinoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.historiaPersonalNinoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'historiaPersonalNinoListModification',
                content: 'Deleted an historiaPersonalNino'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-historia-personal-nino-delete-popup',
    template: ''
})
export class HistoriaPersonalNinoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ historiaPersonalNino }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(HistoriaPersonalNinoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.historiaPersonalNino = historiaPersonalNino;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/historia-personal-nino', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/historia-personal-nino', { outlets: { popup: null } }]);
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
