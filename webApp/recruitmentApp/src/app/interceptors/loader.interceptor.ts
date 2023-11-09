import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable()
export class LoaderInterceptor<T> implements HttpInterceptor {

    public httpRequestArray: Array<HttpRequest<T>> = [];
    private isLoaderVisible: boolean = false;

    constructor(private readonly spinner: NgxSpinnerService
    ) {
    }

    intercept(request: HttpRequest<T>, next: HttpHandler): Observable<HttpEvent<T>> {
        this.httpRequestArray.push(request);
        if (this.httpRequestArray.length > 0 && !this.isLoaderVisible) {
            this.showLoader();
        }

        return next.handle(request)
            .pipe(tap((event: HttpEvent<T>): void => {
                if (event instanceof HttpResponse) {
                      this.httpRequestArray.pop();
                      setTimeout((): void => {
                          if (this.httpRequestArray.length === 0) {
                              this.hideLoader();
                          } else if (this.isLoaderVisible) {
                              this.showLoader();
                          }
                      }, 100);
                }
            }, (): void => {
                this.httpRequestArray.pop();
                setTimeout((): void => {
                    if (this.httpRequestArray.length === 0) {
                        this.hideLoader();
                    } else if (this.isLoaderVisible) {
                        this.showLoader();
                    }
                }, 100);
            }));
    }

    private hideLoader(): void {
        this.isLoaderVisible = false;
        this.spinner.hide();
    }
    private showLoader(): void {
        this.isLoaderVisible = true;
        this.spinner.show();
    }
}
