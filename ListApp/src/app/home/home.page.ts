import { Component, OnInit } from '@angular/core';
import { ListRequestService } from '../services/list-request.service';
import { Router, NavigationExtras } from '@angular/router';
import { ToastController, AlertController } from '@ionic/angular';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {

  private showList: Array<List>

  constructor(private list: ListRequestService, private router: Router
    , private toastCtrl: ToastController, private alertCtrl: AlertController) { }

  ngOnInit() {
    this.getLists();
  }

  async getLists() {
    this.list.getLists().subscribe((res: List) => {
      let i = 0;
      this.showList = new Array(res.length);
      while (res.length > i) {
        this.showList[i] = res[i];
        i++;
      }
    });
  }

  async getListById(list: List) {
    await this.list.getListById(list.idList).subscribe((res: List) => {
      let nav: NavigationExtras = {
        queryParams: {
          special: JSON.stringify(res)
        }
      };
      this.router.navigate(['/in-list'], nav);
    });
  }

  async successCreated() {
    this.alertControllerCreateList();
    this.presentToast();
  }

  async presentToast() {
    const toast = await this.toastCtrl.create({
      message: 'Your list has been created.',
      duration: 2000
    });
    toast.present();
  }

  async presentToastUpdate() {
    const toast = await this.toastCtrl.create({
      message: 'Done :)',
      duration: 2000
    });
    toast.present();
  }

  async presentToastEmpty() {
    const toast = await this.toastCtrl.create({
      message: 'The field is empty :(',
      duration: 2000
    });
    toast.present();
  }

  async alertControllerCreateList() {
    const alert = await this.alertCtrl.create({
      header: 'Create a new list...',
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
          role: 'cancel'
        }, {
          text: 'Create',
          handler: (newData) => {
            if (newData.name==="") {
              this.presentToastEmpty();
            }
            this.list.createList(newData.name).subscribe(() => {
              this.getLists();
            });

          }
        }
      ]
    });
    await alert.present();
  }

  async alertControllerUpdateList(id) {
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
            this.list.updateList(newData.name, id).subscribe(() => {
              this.presentToastUpdate();
              this.getLists();
            });
          }
        }
      ]
    });
    await alert.present();
  }

}

class List {
  idList: number;
  products: Array<Object>;
  listName: string;
  length: number;
}
