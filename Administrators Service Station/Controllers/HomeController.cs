using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Administrators_Service_Station.Models;

namespace Administrators_Service_Station.Controllers
{
    public class HomeController : Controller
    {

        // создаем контекст данных
        AdminContext db = new AdminContext();

        public ActionResult Index()
        {
            return View();
        }


        /// <summary>
        /// main menu -> register an order
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        public ActionResult RegisterAnOrder()
        {
            return View();
        }
        [HttpPost]
        public ActionResult CheckUser(string Name, string Surname)
        {
            int id = 0;
            Array arr = db.Clients.ToArray<Client>();
            foreach(Client c in arr)
            {
                if (c.First_Name.Equals(Name) && c.Last_Name.Equals(Surname))
                    id = c.ClientId;
            }
            if(id != 0)
                return RedirectToAction("Cars/" + id);
            else
                return RedirectToAction("RegisterAnOrder");
        }
        /// <summary>
        /// main menu -> new client
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        public ActionResult NewClient()
        {
            return View();
        }

        [HttpPost]
        public ActionResult NewClient(Client client)
        {
            if (ModelState.IsValid)
            {
                db.Clients.Add(client);
                db.SaveChanges();
                return RedirectToAction("ClientCard/" + client.ClientId);
            }
            else
            {
                return View(client);
            }

        }

        /// <summary>
        /// main menu -> view all clients
        /// </summary>
        /// <returns></returns>
        /// 
        [HttpGet]
        public ActionResult ViewAllClients()
        {
            IEnumerable<Client> clients = db.Clients;
            ViewBag.Clients = clients;
            return View();
        }
        [HttpGet]
        public ActionResult ClientCard(int id)
        {
            Client client = db.Clients.Find(id);
            ViewBag.Client = client;
            return View();
        }

        [HttpGet]
        public ActionResult Cars(int id)
        {
            Client client = db.Clients.Find(id);
            IEnumerable<Car> cars = client.Cars;
            ViewBag.Cars = cars;
            ViewBag.ClientId = id;
            return View();
        }

        [HttpGet]
        public ActionResult AddNewCar(int id)
        {
            ViewBag.Car = new Car();
            ViewBag.ClientId = id;
            return View();
        }

        [HttpPost]
        public ActionResult AddNewCar(Car car)
        {
            if (ModelState.IsValid)
            {
                db.Cars.Add(car);
                Client client = db.Clients.Find(car.ClientId);
                client.Cars.Add(car);
                db.Entry(client).State = System.Data.Entity.EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Cars/" + car.ClientId);
            }
            else
                return RedirectToAction("AddNewCar/" + car.ClientId);
        }

        [HttpGet]
        public ActionResult EditCar(int id)
        {
            ViewBag.Car = db.Cars.Find(id);
            ViewBag.ClientId = ViewBag.Car.ClientId;
            return View("AddNewCar");
        }

        [HttpPost]
        public ActionResult EditCar(Car car)
        {
            if (ModelState.IsValid)
            {
                Car old_car = db.Cars.Find(car.CarId);
                old_car.Make = car.Make;
                old_car.Model = car.Model;
                old_car.Year = car.Year;
                old_car.VIN = car.VIN;
                db.Entry(old_car).State = System.Data.Entity.EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Cars/" + car.ClientId);
            }
            else
                return RedirectToAction("EditCar/"+ car.CarId);
        }

        public ActionResult DeleteCar(int id)
        {
            ViewBag.ErrorMessage = "";
            Car car = db.Cars.Find(id);
            if (car.Orders.Count == 0)
            {
                db.Cars.Remove(car);
                db.SaveChanges();
            }
            else
            {
                ViewBag.ErrorMessage = "This car has orders and cannot be deleted";
            }
            return RedirectToAction("Cars/" + car.ClientId);
        }

        [HttpGet]
        public ActionResult Orders(int id)
        {
            Car car = db.Cars.Find(id);
            ViewBag.Car = car;
            return View();
        }

        [HttpGet]
        public ActionResult EditOrder(int id)
        {
            ViewBag.Order = db.Orders.Find(id);
            ViewBag.CarId = ViewBag.Order.CarId;
            return View("AddNewOrder");
        }

        [HttpPost]
        public ActionResult EditOrder(Order order)
        {
            if (ModelState.IsValid)
            {
                Order old_order = db.Orders.Find(order.OrderId);
                old_order.Date = order.Date;
                old_order.Order_Amount = order.Order_Amount;
                old_order.Order_Status = order.Order_Status;
                db.Entry(old_order).State = System.Data.Entity.EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Orders/" + order.CarId);
            }
            else
                return RedirectToAction("EditOrder/" + order.CarId);
        }

        public ActionResult DeleteOrder(int id)
        {
            Order order = db.Orders.Find(id);
            db.Orders.Remove(order);
            db.SaveChanges();
            return RedirectToAction("Orders/"+order.CarId);
        }

        [HttpGet]
        public ActionResult AddNewOrder(int id)
        {
            ViewBag.Order = new Order();
            ViewBag.CarId = id;
            return View();
        }

        [HttpPost]
        public ActionResult AddNewOrder(Order order)
        {
            if (ModelState.IsValid)
            {
                order.Date = DateTime.Now;
                db.Orders.Add(order);
                Car car = db.Cars.Find(order.CarId);
                car.Orders.Add(order);
                db.Entry(car).State = System.Data.Entity.EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Orders/" + order.CarId);
            }
            else
                return RedirectToAction("AddNewOrder/" + order.CarId);
        }

    }
}