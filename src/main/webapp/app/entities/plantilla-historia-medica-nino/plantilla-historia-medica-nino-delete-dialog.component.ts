import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlantillaHistoriaMedicaNino } from 'app/shared/model/plantilla-historia-medica-nino.model';
import { PlantillaHistoriaMedicaNinoService } from './plantilla-historia-medica-nino.service';

@Component({
    selector: 'jhi-plantilla-historia-medica-nino-delete-dialog',
    templateUrl: './plantilla-historia-medica-nino-delete-dialog.component.html'
})
export class PlantillaHistoriaMedicaNinoDeleteDialogComponent {
    plantillaHistoriaMedicaNino: IPlantillaHistoriaMedicaNino;

    constructor(
        protected plantillaHistoriaMedicaNinoService: PlantillaHistoriaMedicaNinoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.plantillaHistoriaMedicaNinoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'plantillaHistoriaMedicaNinoListModification',
                content: 'Deleted an plantillaHistoriaMedicaNino'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plantilla-historia-medica-nino-delete-popup',
    template: ''
})
export class PlantillaHistoriaMedicaNinoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plantillaHistoriaMedicaNino }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlantillaHistoriaMedicaNinoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.plantillaHistoriaMedicaNino = plantillaHistoriaMedicaNino;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/plantilla-historia-medica-nino', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/plantilla-historia-medica-nino', { outlets: { popup: null } }]);
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
