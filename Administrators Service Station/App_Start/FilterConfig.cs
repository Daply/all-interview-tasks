using System.Web;
using System.Web.Mvc;

namespace Administrators_Service_Station
{
    public class FilterConfig
    {
        public static void RegisterGlobalFilters(GlobalFilterCollection filters)
        {
            filters.Add(new HandleErrorAttribute());
        }
    }
}
