import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEstadoCivil } from 'app/shared/model/estado-civil.model';
import { EstadoCivilService } from './estado-civil.service';

@Component({
    selector: 'jhi-estado-civil-delete-dialog',
    templateUrl: './estado-civil-delete-dialog.component.html'
})
export class EstadoCivilDeleteDialogComponent {
    estadoCivil: IEstadoCivil;

    constructor(
        protected estadoCivilService: EstadoCivilService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.estadoCivilService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'estadoCivilListModification',
                content: 'Deleted an estadoCivil'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-estado-civil-delete-popup',
    template: ''
})
export class EstadoCivilDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ estadoCivil }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EstadoCivilDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.estadoCivil = estadoCivil;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/estado-civil', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/estado-civil', { outlets: { popup: null } }]);
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
