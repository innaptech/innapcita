import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EmailCita } from 'app/shared/model/email-cita.model';
import { EmailCitaService } from './email-cita.service';
import { EmailCitaComponent } from './email-cita.component';
import { EmailCitaDetailComponent } from './email-cita-detail.component';
import { EmailCitaUpdateComponent } from './email-cita-update.component';
import { EmailCitaDeletePopupComponent } from './email-cita-delete-dialog.component';
import { IEmailCita } from 'app/shared/model/email-cita.model';

@Injectable({ providedIn: 'root' })
export class EmailCitaResolve implements Resolve<IEmailCita> {
    constructor(private service: EmailCitaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEmailCita> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EmailCita>) => response.ok),
                map((emailCita: HttpResponse<EmailCita>) => emailCita.body)
            );
        }
        return of(new EmailCita());
    }
}

export const emailCitaRoute: Routes = [
    {
        path: '',
        component: EmailCitaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.emailCita.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: EmailCitaDetailComponent,
        resolve: {
            emailCita: EmailCitaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.emailCita.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: EmailCitaUpdateComponent,
        resolve: {
            emailCita: EmailCitaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.emailCita.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: EmailCitaUpdateComponent,
        resolve: {
            emailCita: EmailCitaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.emailCita.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const emailCitaPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: EmailCitaDeletePopupComponent,
        resolve: {
            emailCita: EmailCitaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.emailCita.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
