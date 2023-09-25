import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlantillaInforme } from 'app/shared/model/plantilla-informe.model';
import { PlantillaInformeService } from './plantilla-informe.service';

@Component({
    selector: 'jhi-plantilla-informe-delete-dialog',
    templateUrl: './plantilla-informe-delete-dialog.component.html'
})
export class PlantillaInformeDeleteDialogComponent {
    plantillaInforme: IPlantillaInforme;

    constructor(
        protected plantillaInformeService: PlantillaInformeService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.plantillaInformeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'plantillaInformeListModification',
                content: 'Deleted an plantillaInforme'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plantilla-informe-delete-popup',
    template: ''
})
export class PlantillaInformeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plantillaInforme }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlantillaInformeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.plantillaInforme = plantillaInforme;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/plantilla-informe', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/plantilla-informe', { outlets: { popup: null } }]);
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
