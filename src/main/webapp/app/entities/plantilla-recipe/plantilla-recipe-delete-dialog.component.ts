import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlantillaRecipe } from 'app/shared/model/plantilla-recipe.model';
import { PlantillaRecipeService } from './plantilla-recipe.service';

@Component({
    selector: 'jhi-plantilla-recipe-delete-dialog',
    templateUrl: './plantilla-recipe-delete-dialog.component.html'
})
export class PlantillaRecipeDeleteDialogComponent {
    plantillaRecipe: IPlantillaRecipe;

    constructor(
        protected plantillaRecipeService: PlantillaRecipeService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.plantillaRecipeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'plantillaRecipeListModification',
                content: 'Deleted an plantillaRecipe'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plantilla-recipe-delete-popup',
    template: ''
})
export class PlantillaRecipeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plantillaRecipe }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlantillaRecipeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.plantillaRecipe = plantillaRecipe;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/plantilla-recipe', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/plantilla-recipe', { outlets: { popup: null } }]);
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
