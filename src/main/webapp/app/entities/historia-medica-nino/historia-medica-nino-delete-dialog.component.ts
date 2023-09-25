import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHistoriaMedicaNino } from 'app/shared/model/historia-medica-nino.model';
import { HistoriaMedicaNinoService } from './historia-medica-nino.service';

@Component({
    selector: 'jhi-historia-medica-nino-delete-dialog',
    templateUrl: './historia-medica-nino-delete-dialog.component.html'
})
export class HistoriaMedicaNinoDeleteDialogComponent {
    historiaMedicaNino: IHistoriaMedicaNino;

    constructor(
        protected historiaMedicaNinoService: HistoriaMedicaNinoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.historiaMedicaNinoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'historiaMedicaNinoListModification',
                content: 'Deleted an historiaMedicaNino'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-historia-medica-nino-delete-popup',
    template: ''
})
export class HistoriaMedicaNinoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ historiaMedicaNino }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(HistoriaMedicaNinoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.historiaMedicaNino = historiaMedicaNino;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/historia-medica-nino', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/historia-medica-nino', { outlets: { popup: null } }]);
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
