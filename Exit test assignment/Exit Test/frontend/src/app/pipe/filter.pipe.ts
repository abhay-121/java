import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../model/Product';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(product: Product[], filterString:any): Product[]{
    if(!product || !filterString){
      return product;
    }

    return product.filter(product=> product.productBrand.toLowerCase().includes(filterString.toLowerCase())
        || product.productCode.toLowerCase().includes(filterString.toLowerCase())
        || product.productName.toLowerCase().includes(filterString.toLowerCase())
    );
  }

}
