using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(Administrators_Service_Station.Startup))]
namespace Administrators_Service_Station
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
