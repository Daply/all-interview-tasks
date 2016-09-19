using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Administrators_Service_Station.Models
{
    public class Order
    {
        public enum Status { In_Progress, Completed, Cancelled };

        // ID
        public int OrderId { get; set; }
        // дата
        [Required(ErrorMessage = "Date is required")]
        public DateTime Date{ get; set; }
        // 
        [Required(ErrorMessage = "Order amount is required")]
        [Range(typeof(double), "0", "10000")]
        public double Order_Amount { get; set; }
        //
        [Required(ErrorMessage = "Order status is required")]
        public Status Order_Status { get; set; }

        public int CarId { get; set; }
    }
}