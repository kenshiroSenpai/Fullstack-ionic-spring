import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ActionSheetController, AlertController, ToastController } from '@ionic/angular';
import { ListRequestService } from '../services/list-request.service';

@Component({
  selector: 'app-in-list',
  templateUrl: './in-list.page.html',
  styleUrls: ['./in-list.page.scss'],
})
export class InListPage implements OnInit {

  private data: any;

  constructor(private route: ActivatedRoute, private actionSheetCtrl: ActionSheetController,
    private list: ListRequestService, private alertCtrl: AlertController,
    private toastCtrl: ToastController) { }

  ngOnInit() {
    this.loadDatas();
  }

  async presentActionSheet(idProduct) {
    const actionSheet = await this.actionSheetCtrl.create({
      header: this.data.belongedProducts.productName,
      buttons: [{
        text: 'Delete',
        role: 'destructive',
        icon: 'trash',
        handler: () => {
          this.list.deleteProductInList(idProduct, this.data.idList).subscribe(() => {
            this.list.getListById(this.data.idList).subscribe((res) => {
              this.data = res;
            });
          });
        }
      }, {
        text: 'Cancel',
        role: 'cancel',
        icon: 'close'
      }]
    });
    await actionSheet.present();
  }

  async updateList() {
    const actionSheet = await this.actionSheetCtrl.create({
      header: this.data.belongedProducts.productName,
      buttons: [{
        text: 'Modify',
        icon: 'swap',
        handler: () => {
          this.alertControllerUpdateList();
        }
      }, {
        text: 'Cancel',
        role: 'cancel',
        icon: 'close'
      }]
    });
    await actionSheet.present();
  }

  async loadDatas() {
    this.route.queryParams.subscribe(params => {
      if (params && params.special) {
        this.data = JSON.parse(params.special);
      }
    });
  }

  async alertControllerUpdateList() {
    const alert = await this.alertCtrl.create({
      header: 'Modify the list...',
      inputs: [
        {
          name: 'name',
          type: 'text',
          placeholder: 'new list...'
        }
      ],
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          cssClass: 'success'
        }, {
          text: 'Modify',
          handler: (newData) => {
            if (newData.name === "") {
              this.presentToastEmpty();
            }
            this.data.listName = newData.name;
            this.list.updateList(newData.name, this.data.idList).subscribe(() => { });
          }
        }
      ]
    });
    await alert.present();
  }

  async presentToastEmpty() {
    const toast = await this.toastCtrl.create({
      message: 'The field is empty :(',
      duration: 2000
    });
    toast.present();
  }

  async alertControllerAddProduct() {
    const alert = await this.alertCtrl.create({
      header: 'Add product...',
      inputs: [
        {
          name: 'name',
          type: 'text',
          placeholder: 'new product...'
        }
      ],
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel'
        }, {
          text: 'Create',
          handler: (newData) => {
            if (newData.name === "") {
              this.presentToastEmpty();
            }
            this.list.addProductInList(newData.name, this.data.idList).subscribe(() => {
              this.list.getListById(this.data.idList).subscribe((res) => {
                this.data = res;
              })
            });
          }
        }
      ]
    });
    await alert.present();
  }

}
